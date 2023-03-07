import React from "react";

const Hexagon = (props) => {
  const { size, fill, stroke } = props;

  // calculate points for the hexagon
  const points = [];
  for (let i = 0; i < 6; i++) {
    const angle = 2 * Math.PI / 6 * i;
    const x = 1 * Math.cos(angle);
    const y = 1 * Math.sin(angle);
    points.push(`${x},${y}`);
  }

  return (
    <svg width={size} height={size} viewBox={`-${size} -${size} ${size * 2} ${size * 2}`}>
      <polygon points={points.join(" ")} fill={fill} stroke={stroke} />
    </svg>
  );
};

export default Hexagon;