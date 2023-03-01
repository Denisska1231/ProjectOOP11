import Link from "next/link";
import React from "react";

export default function Navbar() {
  return (
    <div
      className="d-flex justify-content-center  fw-bold h5 p-3 gap-4"
      style={{ color: "#3C98E0" }}
    >
      <Link href="/">
        <a>Home</a>
      </Link>
      <Link href="/Tutorial">
        <a>Tutorial</a>
      </Link>
      <Link href="/Game">
        <a>Game</a>
      </Link>
      <Link href="/contact">
        <a>About Us</a>
      </Link>
    </div>
  );
}
