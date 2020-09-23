Now that you've had some thyme to read about Thymeleaf, it's time to get on the clock and put that knowledge to use. You're going to create an action figure product catalog.

## Getting Started

```no-highlight
et get java-action-figures
cd java-action-figures
idea .
```

The controller, model and service layer have been provided for you. Your job is to build out the relevant Thymeleaf templates.
Spring convention has our templates living in `src/main/resources/templates/*` with the `*` being the class in question. The first place most users visit is the list of products. This mapping in the controller will take care of passing the data to Thymeleaf, you'll need to create your `index.html` and build out the template.

***HINT: Look at the related controller action to see what data you have available to you in each template.***

### List Products

Your product manager wants the following information to appear on the index page (`/products').

- For each product, I should see their name and price.
- For each product, there should be a link to view more details about the product which takes me to `/products/show/<productId>` where `productId` is the id of the product

{% show_solution %}

```html
<!DOCTYPE html>
<html xmlns:th="https://www.thymeleaf.org">
<head>
  <link rel="stylesheet" type="text/css" href="/css/main.css">
  <title>Java Product Shop</title>
</head>
<body>
<h1>Product Options</h1>

<div th:each="product : ${products}">
  <div th:object="${product}">
    <h2 th:text="*{name}">Product name</h2>
    <p th:text="'$' + *{price}">Product price</p>
    <a th:href="'/products/show/' + ${product.id}">Learn more</a>
  </div>
</div>
<a href="/products/new">Add a new product</a>
</body>
</html>
```

{% endshow_solution %}

### Display an Individual Product

When a user visits the show page for an item you have been asked to display the following information

- When I navigate to `/products/show/<productId>` where `productId` is the id of the product, I am presented with the relevant product information
  - product name
  - price
  - description
- There should be a link that takes me back to the index page
- There should be a link which takes me to the form to add a new product

{% show_solution %}

```html
  <!DOCTYPE html>
<html xmlns:th="https://www.thymeleaf.org">
<head>
  <link rel="stylesheet" type="text/css" href="/css/main.css">
  <title>Java Product Shop</title>
</head>
<body>


<div th:object="${product}">
  <h1 th:text="*{name}">Product name</h1>
  <p th:text="'$' + *{price}">Product price</p>
  <p th:text="*{description}">Product description</p>
</div>

<a href="/products">See all products</a>
<a href="/products/new">Add a new product</a>
</body>
</html>
```

{% endshow_solution %}

### Add a New Product

There is rumbling around the office that your app might soon become a listing place for collectors to sell their figures. You now need to create a form for all those hoarders to list their original 1979 Tie Fighter.

- When I navigate to `/products/new`, I am presented with a form to input a new product
- I must enter the product's name, price, and description
  - Use the appropriate input types for the data the user must supply
  - Don't worry about server side validation
  - Make sure your new product appears in the list, and that you can visit the show page.

{% show_solution %}

```html
<!DOCTYPE html>
<html xmlns:th="https://www.thymeleaf.org">
<head>
<title>Java Product Shop</title>
</head>
<body>
<h1>Add a New Product</h1>

<form action="#" th:action="@{/products}" th:object="${product}" method="POST">

<div>
  <label th:for="name">Name</label>
  <input type="text" th:field="*{name}" />
</div>

<div>
  <label th:for="price">Price</label>
  <input type="number" step="0.01" th:field="*{price}" />
</div>


<div>
  <label th:for="description">Description</label>
  <input type="text" th:field="*{description}" />
</div>

<div>
  <input type="submit" value="Add Product" />
</div>
<a href="/products">Back to list of products</a>
</form>
</body>
</html>

<!--Notice we use the `step` HTML attribute to ensure the price will be valid.-->

```

{% endshow_solution %}
