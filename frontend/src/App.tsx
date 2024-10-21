import { useEffect, useState } from "react";
import Finished from "./components/Finished";
import Loading from "./components/Loading";
import Playing from "./components/Playing";
import SignInRequired from "./components/SignInRequired";
import "./index.css";
import { getCurrentLevel, verifySolution } from "./service/levels";
import { GameState, type Level } from "./types";

function App() {
  const test: Level = {
    id: 0,
    personCount: 0,
    knightCount: 0,
    description: "",
    persons: [
      {
        name: "",
        dialogue: "",
      },
    ],
    levelNumber: 0,
  };

  const [level, setLevel] = useState(test);
  const [solution, setSolution] = useState([true]);
  const [gameState, setGameState] = useState(GameState.Loading);

  useEffect(() => {
    nextLevel();
  }, []);

  async function nextLevel() {
    const currentLevel = await getCurrentLevel();
    if (typeof currentLevel === "string") {
      if (currentLevel === "signin") setGameState(GameState.SignInRequired);
      else if (currentLevel === "nomorelevels") setGameState(GameState.Finished);
    } else if (currentLevel) {
      setLevel(currentLevel);
      setSolution(new Array(currentLevel.personCount).fill(false));
      setGameState(GameState.Playing);
    }
  }

  function changeSolution(index: number) {
    const newSolution = [...solution];
    newSolution[index] = !newSolution[index];
    setSolution(newSolution);
  }

  async function submitSolution() {
    const isCorrect = await verifySolution(solution);
    if (isCorrect) nextLevel();
  }

  let gameStateJSX: JSX.Element;
  switch (gameState) {
    case GameState.SignInRequired:
      gameStateJSX = <SignInRequired />;
      break;
    case GameState.Playing:
      gameStateJSX = (
        <Playing
          level={level}
          solution={solution}
          changeSolution={changeSolution}
          submitSolution={submitSolution}
        />
      );
      break;
    case GameState.Finished:
      gameStateJSX = <Finished />;
      break;
    default:
      gameStateJSX = <Loading />;
  }

  return (
    <div
      id="content"
      className="min-h-screen flex flex-col items-center justify-center bg-amber-50 p-4 overflow-y-auto"
    >
      <h1 className="text-gray-900 text-3xl md:text-4xl font-bold text-center mb-4">
        Knights & Knaves
      </h1>
      <p className="text-gray-900 text-base md:text-lg max-w-md text-wrap text-center mb-6">
        {level.description}
      </p>
      {gameStateJSX}
      <p className="mt-10 text-gray-700 text-sm md:text-base text-center w-full">
        This is a demo project by{" "}
        <a
          href="https://github.com/ljcoogan"
          target="_blank"
          rel="noopener noreferrer"
          className="text-sky-500 underline"
        >
          Liam Coogan
        </a>
      </p>
    </div>
  );
}

export default App;
