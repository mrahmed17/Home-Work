function calculatePercentage(price, percentage) {
  return (price * percentage) / 100;
}

let productPrice = parseInt(prompt('Enter the product price'));
let percentageValue = parseInt(prompt('Enter the percentage'));

let result = calculatePercentage(productPrice, percentageValue);

// alert('The ' + percentageValue + '% of ' + productPrice + ' is: ' + result);

// If you want to display the result in a paragraph, use the following code:

document.getElementById('resultParagraph').innerText =
  'The ' + percentageValue + '% of ' + productPrice + ' is: ' + result;

/*
// Function to calculate the percentage of a price
function calculatePercentage(price, percentage) {
  return (price * percentage) / 100;
}

// Example usage:
let productPrice = prompt("Enter the product price:");
let percentageValue = prompt("Enter the percentage:");

// Ensure the input is converted to numbers
productPrice = Number(productPrice);
percentageValue = Number(percentageValue);

// Calculate the result
let result = calculatePercentage(productPrice, percentageValue);

// Display the result in an alert box
alert("The " + percentageValue + "% of $" + productPrice + " is: $" + result);

// If you want to display the result in a paragraph, use the following code:
// document.getElementById("resultParagraph").innerText = "The " + percentageValue + "% of $" + productPrice + " is: $" + result;
*/
