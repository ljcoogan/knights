import type { Level } from "../types";

export default function Playing({
  level,
  solution,
  changeSolution,
  submitSolution,
}: {
  level: Level;
  solution: boolean[];
  changeSolution: (index: number) => void;
  submitSolution: () => void;
}) {
  const levelList = level.persons.map((line, index) => (
    <li key={line.name} className="my-3 flex items-center justify-between w-full">
      <div className="flex items-center">
        <span className="mr-2">
          <b>{line.name}</b>: {line.dialogue}
        </span>
      </div>
      <div className="flex items-center">
        <input
          type="checkbox"
          checked={solution[index]}
          onChange={() => changeSolution(index)}
          className="ml-2"
        />
      </div>
    </li>
  ));

  return (
    <div
      id="game"
      className="flex flex-col items-center justify-between w-full max-w-md p-4 bg-white rounded-lg shadow-md"
    >
      <p className="text-gray-900 text-lg font-medium">Level {level.levelNumber}</p>
      <p className="text-gray-700 text-base text-center mb-4 italic">
        This level has <b>{level.knightCount}</b> knight{level.knightCount > 1 ? "s" : ""}.
      </p>
      <ul className="list-none p-0 w-full">{levelList}</ul>
      <button
        type="button"
        onClick={submitSolution}
        className="bg-red-500 hover:bg-red-700 text-white font-bold py-3 px-6 rounded mt-4 w-full"
      >
        Submit Solution
      </button>
    </div>
  );
}
