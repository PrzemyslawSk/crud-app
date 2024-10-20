import { Link } from "react-router-dom";

function HomePageComponent() {
  return (
    <div className="HomePageComponent">
      <h1>Welcome!</h1>
      <div>
        Manage your products - <Link to="/products">Manage</Link>
      </div>
    </div>
  );
}

export default HomePageComponent;
