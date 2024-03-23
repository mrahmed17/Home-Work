function submitForm(event) {
  event.preventDefault();

  let rName = document.getElementById('name').value;
  if (rName == '') {
    alert('Enter Your Name');
    return;
  } else if (rName<=3) {
    alert('Name must have 4 letter')
  };

  let contact = document.getElementById('contact').value;
  let remarks = document.getElementById('remarks').value;
  let gender = document.querySelector('input[name="gender"]:checked');
  let course = document.querySelectorAll('input[name="course"]:checked');
  let courseValue = [];
  
};