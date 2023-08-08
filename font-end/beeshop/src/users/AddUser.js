import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function AddUser() {

    let nav = useNavigate()

    const [user, setUser] = useState({
        username : "",
        password : "",
        email : "",
        phone : "",
        address : "",
        role : ""
    })

    const {username, password, email, phone, address, role} = user

    const onInputHandle = (e) => {
        setUser({...user, [e.target.name]:e.target.value})
    }

    const onSubmitHandle = async (e) => {
        e.preventDefault();
        console.log(user)
        await axios.post("http://localhost:8080/user/add", user)
        nav("/user")
    }

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Register User</h2>
          <form onSubmit={onSubmitHandle}>
            <div class="mb-3">
              <label htmlFor="username" className="form-label">
                Username
              </label>
              <input
                type="text"
                className="form-control"
                name="username"
                id="username"
                value={username}
                onChange={onInputHandle}
              />
              <div id="emailHelp" className="form-text">
                We'll never share your email with anyone else.
              </div>
            </div>
            <div className="mb-3">
              <label htmlFor="password" className="form-label">
                Password
              </label>
              <input
                type="password"
                className="form-control"
                name="password"
                id="password"
                value={password}
                onChange={onInputHandle}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="email" className="form-label">
                Email
              </label>
              <input
                type="email"
                className="form-control"
                name="email"
                id="email"
                value={email}
                aria-describedby="emailHelp"
                onChange={onInputHandle}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="phone" className="form-label">
                Phone
              </label>
              <input
                type="text"
                className="form-control"
                name="phone"
                id="phone"
                value={phone}
                onChange={onInputHandle}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="address" className="form-label">
                Address
              </label>
              <input
                type="text"
                className="form-control"
                name="address"
                id="address"
                value={address}
                onChange={onInputHandle}
              />
            </div>
            <div className="mb-3 form-check">
              <div className="col-md-3">
                <input
                  type="radio"
                  className="form-check-input"
                  name="role"
                  id="customer"
                  checked={role === "Customer"}
                  value={"Customer"}
                  onChange={onInputHandle}
                />
                <label className="form-check-label" htmlFor="customer">
                  Customer
                </label>
              </div>
              <div className="col-md-3">
                <input
                  type="radio"
                  className="form-check-input"
                  name="role"
                  id="provider"
                  checked={role === "Provider"}
                  value={"Provider"}
                  onChange={onInputHandle}
                />
                <label className="form-check-label" htmlFor="provider">
                  Provider
                </label>
              </div>
            </div>

            <button type="submit" className="btn btn-primary">
              Submit
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}
