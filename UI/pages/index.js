import React from "react";
import Navbar from "../components/Navbar";

export default function Home() {
  return (
    <div>
      <Navbar />
      <div className="mx-auto" style={{ maxWidth: "800px" }}>
        <div className="vstack rounded-4 bg-white p-lg-4">
          <div className="d-flex">
            <img
              src="/monkeysan.jpg"
              width="200"
              height="200"
              style={{ objectFit: "cover", minWidth: "200px" }}
              className="rounded-circle border-2"
            />
            <div className="d-flex flex-column p-2 rounded-3 ms-3 gap-3">
              <span className=" h1">Project Group 11 </span>
              <span style={{ color: "gray" }}>แนะนำเล่นในคอม</span>
            </div>
          </div>
          <div className="text-center h4">
            <br />
            <br />
            <span className=" h3 border-5 p-3">About Game</span>
            <br />
            <br />
          </div>
          {/* <!-- Cardsssss? --> */}
          <div className="vstack">
            <div className="card mb-3" style={{ maxWidth: "1000px" }}>
              <div className="row g-0">
                <div className="col-md-4">
                  <img src="/hexgrid.jpg" className="img-fluid rounded-start" />
                </div>
                <div className="col-md-8">
                  <div className="card-body">
                    <h4 className="card-title">Project OOP</h4>
                    <p className="card-text " style={{ color: "gray" }}>
                      สร้างเมืองของคุณเพื่อต่อสู้ แย่งชิง เอาชนะ
                      และเพื่อทรัพยากรต่างๆ ได้ในเกม"ยังคิดไม่ออก"สำหรับ 2 คน
                      สร้าง สู้ ใช้จ่าย และเก็บภาษีเพื่อเมืองที่ยิ่งใหญ่
                    </p>
                  </div>
                </div>
              </div>
            </div>
            {/* <!-- card22222222 --> */}
            <div className="card mb-3" style={{ maxWidth: "1000px" }}>
              <div className="row g-0">
                <div className="col-md-4">
                  <img src="/Tag.jpg" class="img-fluid rounded-start" />
                </div>
                <div className="col-md-8">
                  <div className="card-body">
                    <h4 className="card-title">Tag</h4>
                    <p className="card-text " style={{ color: "gray" }}>
                      เล่นระหว่างพัฒนา , turn-base
                    </p>
                  </div>
                </div>
              </div>
            </div>
            <div className="text-center" style={{ color: "#2173de" }}>
              <span> ⓒ 2023 create by Group11.</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
