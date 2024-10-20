import axios from "axios";

export const apiClient = axios.create({
  baseURL: "http://localhost:8080",
});

export const retrieveAllProductsApi = () => apiClient.get("/api/products");

export const deleteProductApi = (id) => apiClient.delete(`/api/products/${id}`);

export const retrieveProductApi = (id) => apiClient.get(`/api/products/${id}`);

export const updateProductApi = (id, product) =>
  apiClient.put(`/api/products/${id}`, product);

export const createProductApi = (product) =>
  apiClient.post("/api/products", product);
