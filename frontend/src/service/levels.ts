import type { Level } from "../types";

export async function getCurrentLevel() {
  try {
    const res = await fetch("/api/level", {
      method: "GET",
      credentials: "include",
      redirect: "manual",
    });

    if (res.type === "opaqueredirect") {
      return "signin";
    }

    if (res.status === 404) {
      return "nomorelevels";
    }

    const body: Level = await res.json();
    return body;
  } catch (e) {
    console.error("ERROR ", e);
  }
}

export async function verifySolution(solution: boolean[]): Promise<boolean> {
  try {
    const res = await fetch("/api/level", {
      method: "POST",
      redirect: "follow",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(solution),
    });

    if (!res.ok) {
      console.error("Request unsuccessful.");
      return false;
    }

    return await res.json();
  } catch (e) {
    console.error("ERROR ", e);
    return false;
  }
}
