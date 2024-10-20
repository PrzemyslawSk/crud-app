import { useNavigate, useParams } from "react-router-dom";
import {
  retrieveProductApi,
  updateProductApi,
  createProductApi,
} from "./api/ProductsApiService";
import { useEffect, useState } from "react";
import { Formik, Form, Field, ErrorMessage } from "formik";

function ProductComponent() {
  const { id } = useParams();

  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [price, setPrice] = useState("");

  const navigate = useNavigate();

  useEffect(() => retrieveProduct(), [id]);

  function retrieveProduct() {
    if (id != -1)
      retrieveProductApi(id)
        .then((res) => {
          setName(res.data.name);
          setDescription(res.data.description);
          setPrice(res.data.price);
        })
        .catch((err) => console.log(err));
  }

  function onSubmit(newProduct) {
    const product = {
      id: id,
      name: newProduct.name,
      description: newProduct.description,
      price: newProduct.price,
    };

    if (id == -1) {
      createProductApi(product)
        .then(() => {
          navigate("/products");
        })
        .catch((err) => console.log(err));
    } else {
      updateProductApi(id, product)
        .then(() => {
          navigate("/products");
        })
        .catch((err) => console.log(err));
    }
  }

  function validate(values) {
    let errors = {};

    // Name
    if (values.name.length < 5) {
      errors.name = "Name should contain at least 5 characters.";
    }
    if (values.name.length > 20) {
      errors.name = "Name should not be longer than 20 characters.";
    }

    // Description
    if (values.description.length > 50) {
      errors.description =
        "Description should not be longer than 50 characters.";
    }

    // Price
    if (values.price < 0 || values.price == "" || values.price == null) {
      errors.price = "Enter correct price.";
    }

    return errors;
  }

  return (
    <div className="container">
      <h1>Enter product details</h1>
      <div>
        <Formik
          initialValues={{ name, description, price, id }}
          enableReinitialize={true}
          onSubmit={onSubmit}
          validate={validate}
          validateOnChange={false}
          validateOnBlur={false}
        >
          {() => (
            <Form>
              <ErrorMessage
                name="name"
                component="div"
                className="alert alert-warning"
              />
              <ErrorMessage
                name="description"
                component="div"
                className="alert alert-warning"
              />
              <ErrorMessage
                name="price"
                component="div"
                className="alert alert-warning"
              />
              {id != -1 && (
                <fieldset className="form-group">
                  <label>Id</label>
                  <Field
                    type="text"
                    className="form-control"
                    name="id"
                    disabled
                  />
                </fieldset>
              )}
              <fieldset className="form-group">
                <label>Name</label>
                <Field type="text" className="form-control" name="name" />
              </fieldset>
              <fieldset className="form-group">
                <label>Description</label>
                <Field
                  type="text"
                  className="form-control"
                  name="description"
                />
              </fieldset>
              <fieldset className="form-group">
                <label>Price</label>
                <Field type="number" className="form-control" name="price" />
              </fieldset>
              <div>
                <button className="btn btn-primary m-5" type="submit">
                  Save
                </button>
              </div>
            </Form>
          )}
        </Formik>
      </div>
    </div>
  );
}

export default ProductComponent;
