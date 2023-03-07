import React from "react";
import Navbar from "../components/Navbar";
import HexGridDemo from "./Grid.js";

export default function Game() {
  return (
    <div className="text-center">
      <Navbar />
      <HexGridDemo />
      <div
      className="d-flex justify-content-center  fw-bold h5 p-3 gap-4"
      style={{ color: "#3C98E0" }}
    >
      P{} point {} 
    </div>
    <div>
    <button class="btn-1 ">
      collect 
      </button>
      <button class="btn-1">
      move
      </button>
      <button class="btn-1">
      shoot
      </button>
      <button class="btn-1">
      END TURN
      </button>
    </div>

    </div>
  );
}

