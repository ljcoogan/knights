export interface Level {
  id: number;
  personCount: number;
  knightCount: number;
  description: string;
  persons: Person[];
  levelNumber: number;
}

interface Person {
  name: string;
  dialogue: string;
}

export enum GameState {
  Loading = 1,
  SignInRequired = 2,
  Playing = 3,
  Finished = 4,
}
