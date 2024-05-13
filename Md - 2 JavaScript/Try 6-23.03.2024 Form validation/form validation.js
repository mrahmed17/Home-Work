function submitForm() {
    let rName = document.getElementById('name').value;
  let contact = document.getElementById('contact').value;
  let remarks = document.getElementById('remarks').value;
  let location = document.getElementById("location").value;
  let gender = document.querySelector("input[name='gender']:checked");
  let course = document.querySelectorAll("input[name='course']:checked");


    let courseValue = [];
    for (let i = 0; i < course.length; i++) {
      courseValue.push(course[i].value);
      };

  let output = "Name: " + rName + "\n"; 
  output += "Contact: " + contact + "\n"; 
  output += "Remarks: " + remarks + "\n"; 
  output += "Location: " + location + "\n"; 
  output += "Gender: " + gender.value + "\n"; 
  output += "Course: " + courseValue + "\n"; 

  let nWindow = window.open("", '_blank');
  nWindow.document.write("<pre>" + output + "</pre>");
};
  