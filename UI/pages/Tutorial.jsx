import React from "react";
import Navbar from "../components/Navbar";

export default function Tutorial() {
  return (
    <div>
      <Navbar />
      <div className="mx-auto" style={{ maxWidth: "800px" }}>
        <div className="vstack  p-lg-4">
          <div className="text-center">
            <span className="fw-bold h3">Tutorial(เร็วๆนี้)</span>
            <br />
            <br />
          </div>
          {/* <!-- Credit text --> */}
          <div className="text-center" style={{ color: "#2173de " }}>
            <span> ⓒ 2022 Resume by Natchanon.</span>
          </div>
        </div>
      </div>
    </div>
  );
}
