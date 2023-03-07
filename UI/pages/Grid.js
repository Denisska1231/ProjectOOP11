import React from "react";
import HexagonGrid from "./HexagonGrid";
// import Hexagon from "react-hexagon";
import Hexagon from "./Hexagon";

const x=5;
const y=5;

export const datamap = [];
for (let loc = 0; loc < x*y; loc++) {
  datamap[loc]='null';
}
const HexGrid = () => {
  const getHexProps = (hexagon) => {
    return {
      style: {
        fill: "#007aff",
        stroke: "white"
      },
      onClick: () => alert(`Hexagon number ${hexagon} `)
    };
  };
  const renderHexagonContent = (hexagon) => {
    return (
      <text
        x="50%"
        y="50%"
        fontSize={70}
        fontWeight="lighter"
        style={{ fill: "white" }}
        textAnchor="middle"
      >
        {hexagon}
      </text>
    );
  };
  const hexagons = [];
  let i=-1;
  for (let row = 0; row < x; row++) {
    if(i <= 1){i++}
    for (let col = 0; col < y; col++) {
      const hexagon = (
        <Hexagon key={`${row}-${col}`} style={{ stroke: "white", fill: "#A1A1A1" }} flatTop>

        </Hexagon>
      );
      hexagons.push(i);
      i++
    }
  }

  return (
    <div>
      <HexagonGrid
        hexagons={hexagons}
        gridWidth={500}
        gridHeight={500}
        rowsx={4}
        columsy={5}
        hexProps={getHexProps}
        renderHexagonContent={renderHexagonContent}
      />
    </div>
  );
};

export default HexGrid;
