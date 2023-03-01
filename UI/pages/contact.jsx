import React from "react";
import Navbar from "../components/Navbar";

export default function AboutUs() {
  return (
    <div>
      <Navbar />
      <div className="mx-auto" style={{ maxWidth: "800px" }}>
        <div className="vstack  p-lg-4">
          <div className="text-center h3">
            <span>Contact </span>
            <br />
            <br />
          </div>

          <div className="d-flex flex-column p-2 rounded-3 ms-3 gap-3">
            <div className="text">
              <span> Front End Natchanon Nantasri 640612086</span>
            </div>
            <div className="text">
              <span> Backed End Jethaphon Kobkam 640612083</span>
            </div>
            <div className="text">
              <span>
                {" "}
                ติดตามเราใน Git ได้ที่{" "}
                <a
                  style={{ color: "red" }}
                  href="https://github.com/Denisska1231/ProjectOOP11"
                  target="_blank"
                  rel="noreferrer"
                >
                  ไปที่ Git
                </a>
              </span>
            </div>
          </div>
          <div className="text-center" style={{ color: "#2173de" }}>
            <span> ⓒ 2022 Resume by Natchanon.</span>
          </div>
        </div>
      </div>
    </div>
  );
}
