import React from "react";
import Navbar from "../components/Navbar";
import HexGridDemo from "./Grid.js";

export default function Game() {
  return (
    <div className="text-center">
      <Navbar />
      <HexGridDemo />
    </div>
  );
}
