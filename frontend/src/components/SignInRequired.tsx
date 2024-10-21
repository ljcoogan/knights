export default function SignInRequired() {
  return (
    <div
      id="game"
      className="flex flex-col items-center justify-between w-full max-w-md p-4 bg-white rounded-lg shadow-md"
    >
      <p className="text-gray-900 text-xl text-center">
        You must sign in to Google to play this game.
      </p>
      <a
        href="http://localhost:8080/oauth2/authorization/google"
        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-3 px-6 rounded mt-4 w-full text-center"
      >
        Sign In
      </a>
    </div>
  );
}
