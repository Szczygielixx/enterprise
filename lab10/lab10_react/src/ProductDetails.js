import React from 'react';
import { useParams, Link } from 'react-router-dom';

const ProductDetails = ({ products }) => {
  const { id } = useParams();

  // Find product by id. Note: id from params is a string.
  const product = products.find(p => p.id == id);

  if (!product) {
    return <div>Product not found.</div>;
  }

  return (
    <div>
      <h1>{product.title}</h1>
      Category: {product.category}<br />
      Brand: {product.brand}<br />
      Description: {product.description}<br />
      Price: ${product.price}<br />
      <img src={product.thumbnail} alt={product.title} style={{maxWidth: '400px', maxHeight: '400px'}}/>
      <br />
      <Link to="/">Back to list</Link>
    </div>
  );
};

export default ProductDetails; 