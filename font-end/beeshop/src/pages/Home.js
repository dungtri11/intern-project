import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

export default function Home() {
    const [user, setUser] = useState([])

    useEffect(() => {
        getData()
    }, [])

    const getData = async () => {
        const data = await axios.get("http://localhost:8080/user")
        setUser(data.data)
    }

  return (
    <div className="container">
      <div className="py-4">
      <Link className="btn btn-primary mx-2" to={"/user/add"}>Add User</Link>
        <table className="table border shadow">
          <thead>
            <tr>
              <th scope="col">ID</th>
              <th scope="col">Username</th>
              <th scope="col">Phone</th>
              <th scope="col">Email</th>
              <th scope="col">Address</th>
              <th scope="col">Role</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            {user.map((e) => (
                <tr>
                <th scope="row">{e.id}</th>
                <td>{e.username}</td>
                <td>{e.phone}</td>
                <td>{e.email}</td>
                <td>{e.address}</td>
                <td>{e.role}</td>
                <td>
                    <Link className="btn btn-primary mx-2">View</Link>
                    <Link className="btn btn-outline-primary mx-2">Edit</Link>
                    <Link className="btn btn-danger mx-2">Remove</Link>
                </td>
              </tr>
            ))}
            
          </tbody>
        </table>
      </div>
    </div>
  );
}
