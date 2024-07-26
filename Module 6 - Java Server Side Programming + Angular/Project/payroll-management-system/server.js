const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const mysql = require('mysql');

const app = express();
const port = 3000;

app.use(cors());
app.use(bodyParser.json());

// MySQL connection
const db = mysql.createConnection({
  host: 'localhost',
  user: 'root', // replace with your MySQL username
  password: '', // replace with your MySQL password
  database: 'employee_management' // replace with your database name
});

db.connect(err => {
  if (err) {
    console.error('Error connecting to the database:', err);
    return;
  }
  console.log('Connected to the MySQL database.');
});

// Sample route to test the connection
app.get('/', (req, res) => {
  res.send('Backend server is running');
});

// Route to get employees
app.get('/employees', (req, res) => {
  const sql = 'SELECT * FROM employees';
  db.query(sql, (err, result) => {
    if (err) {
      return res.status(500).send(err);
    }
    res.json(result);
  });
});

// Route to add a new employee
app.post('/employees', (req, res) => {
  const newEmployee = req.body;
  const sql = 'INSERT INTO employees SET ?';
  db.query(sql, newEmployee, (err, result) => {
    if (err) {
      return res.status(500).send(err);
    }
    res.json(result);
  });
});

// Add more routes as needed

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
