import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import NavBar from "./layouts/NavBar";
import Home from "./pages/Home";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AddUser from "./users/AddUser";

function App() {
  return (
    <div className="App">
      <Router>
        <NavBar />
        <Routes>
          <Route exact path="/user" element={<Home />} />
          <Route exact path="/user/add" element={<AddUser />} />
          
        </Routes>
      </Router>
    </div>
  );
}

export default App;
