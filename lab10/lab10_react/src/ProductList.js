import React, { useState } from 'react';
import ProductItem from './ProductItem';

const ProductList = ({ products }) => {
  const [filter, setFilter] = useState('');

  const handleFilterChange = (event) => {
    setFilter(event.target.value);
  };

  const filteredProducts = products.filter(product =>
    product.title.toLowerCase().includes(filter.toLowerCase())
  );

  return (
    <div>
      <h1>List of products</h1>
      <div>
        <label htmlFor="filter">Filter by product title: </label>
        <input
          type="text"
          id="filter"
          value={filter}
          onChange={handleFilterChange}
        />
      </div>
      <ul>
        {filteredProducts.map(product => (
          <ProductItem 
            key={product.id}
            id={product.id}
            title={product.title} 
            brand={product.brand} 
          />
        ))}
      </ul>
    </div>
  );
};

export default ProductList; 