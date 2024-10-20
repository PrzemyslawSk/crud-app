import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import {
  retrieveAllProductsApi,
  deleteProductApi,
} from "./api/ProductsApiService";

function MainContentComponent() {
  const [products, setProducts] = useState([]);

  const [message, setMessage] = useState(null);

  const navigate = useNavigate();

  useEffect(() => refreshProducts(), []);

  function refreshProducts() {
    retrieveAllProductsApi()
      .then((res) => {
        setProducts(res.data);
      })
      .catch((err) => console.log(err));
  }

  function deleteProduct(id) {
    deleteProductApi(id)
      .then(() => {
        setMessage(`Product with id ${id} has been deleted.`);
        refreshProducts();
      })
      .catch((err) => console.log(err));
  }

  function updateProduct(id) {
    navigate(`/product/${id}`);
  }

  function addNewProduct() {
    navigate(`/product/-1`);
  }

  return (
    <div className="container">
      <h1>List of products</h1>
      {message && <div className="alert alert-warning">{message}</div>}
      <div>
        <table className="table">
          <thead>
            <tr>
              <th>Name</th>
              <th>Description</th>
              <th>Price</th>
              <th>Update</th>
              <th>Delete</th>
            </tr>
          </thead>
          <tbody>
            {products.map((product) => (
              <tr key={product.id}>
                <td>{product.name}</td>
                <td>{product.description}</td>
                <td>{product.price}</td>
                <td>
                  <button
                    className="btn btn-primary"
                    onClick={() => updateProduct(product.id)}
                  >
                    Update
                  </button>
                </td>
                <td>
                  <button
                    className="btn btn-danger"
                    onClick={() => deleteProduct(product.id)}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <div className="btn btn-primary m-5" onClick={addNewProduct}>
        Add new product
      </div>
    </div>
  );
}
export default MainContentComponent;
