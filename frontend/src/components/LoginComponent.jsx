import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "./security/AuthContext";
import "./css/LoginComponent.css";

function LoginComponent() {
  const [username, setUsername] = useState("przemyslawsk");
  const [password, setPassword] = useState("");
  const [showErrorMessage, setShowErrorMessage] = useState(false);

  const navigate = useNavigate();

  const authContext = useAuth();

  function handleUsernameChange(event) {
    setUsername(event.target.value);
  }

  function handlePasswordChange(event) {
    setPassword(event.target.value);
  }

  async function handleSubmit() {
    if (await authContext.login(username, password)) {
      navigate(`/home`);
    } else {
      setShowErrorMessage(true);
    }
  }

  return (
    <div className="wrapper">
      <div className="form-signin">
        <h2 className="form-signin-heading text-center">Log in</h2>
        {showErrorMessage && (
          <div className="errorMessage">
            Authentication failed. Please, try again.
          </div>
        )}
        <input
          className="form-control"
          type="text"
          name="username"
          value={username}
          onChange={handleUsernameChange}
          placeholder="Username"
        />
        <input
          className="form-control"
          type="password"
          name="password"
          value={password}
          onChange={handlePasswordChange}
          placeholder="Password"
        />
        <button
          className="btn btn-lg btn-primary w-100"
          name="login"
          onClick={handleSubmit}
        >
          Login
        </button>
      </div>
    </div>
  );
}

export default LoginComponent;
