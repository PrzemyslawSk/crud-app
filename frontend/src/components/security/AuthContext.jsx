import { createContext, useContext, useState } from "react";
import { apiClient } from "../api/ProductsApiService";
import { executeJwtAuthService } from "../api/AuthenticationApiService";

export const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

export default function AuthProvider({ children }) {
  const [isAuthenticated, setAuthenticated] = useState(false);
  const [token, setToken] = useState(null);

  async function login(username, password) {
    try {
      const response = await executeJwtAuthService(username, password);

      if (response.status == 200) {
        const jwtToken = "Bearer " + response.data.token;

        setAuthenticated(true);
        setToken(jwtToken);

        // Adding authorization header into each of the api calls
        apiClient.interceptors.request.use((config) => {
          config.headers.Authorization = jwtToken;
          return config;
        });

        return true;
      } else {
        logout();
        return false;
      }
    } catch (error) {
      logout();
      return false;
    }
  }

  function logout() {
    setAuthenticated(false);
    setToken(null);
  }

  return (
    <AuthContext.Provider value={{ isAuthenticated, login, logout, token }}>
      {children}
    </AuthContext.Provider>
  );
}
