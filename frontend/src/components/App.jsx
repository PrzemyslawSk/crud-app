import HomePageComponent from "./HomePageComponent";
import HeaderComponent from "./HeaderComponent";
import MainContentComponent from "./MainContentComponent";
import LoginComponent from "./LoginComponent";
import LogoutComponent from "./LogoutComponent";
import ErrorComponent from "./ErrorComponent";
import ProductComponent from "./ProductComponent";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import "./css/App.css";
import AuthProvider, { useAuth } from "./security/AuthContext";

function AuthenticatedRoute({ children }) {
  const authContext = useAuth();

  if (authContext.isAuthenticated) {
    return children;
  }

  return <Navigate to="/" />;
}

function App() {
  return (
    <div className="App">
      <AuthProvider>
        <BrowserRouter>
          <HeaderComponent />
          <Routes>
            <Route path="/" element={<LoginComponent />} />
            <Route
              path="/home"
              element={
                <AuthenticatedRoute>
                  <HomePageComponent />
                </AuthenticatedRoute>
              }
            />
            <Route
              path="/products"
              element={
                <AuthenticatedRoute>
                  <MainContentComponent />
                </AuthenticatedRoute>
              }
            />
            <Route
              path="/product/:id"
              element={
                <AuthenticatedRoute>
                  <ProductComponent />
                </AuthenticatedRoute>
              }
            />
            <Route
              path="/logout"
              element={
                <AuthenticatedRoute>
                  <LogoutComponent />
                </AuthenticatedRoute>
              }
            />

            <Route path="*" element={<ErrorComponent />} />
          </Routes>
        </BrowserRouter>
      </AuthProvider>
    </div>
  );
}

export default App;
