import { apiClient } from "./ProductsApiService";

export const executeJwtAuthService = (username, password) =>
  apiClient.post("/authenticate", { username, password });
