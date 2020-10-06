function checkPassword(form) {
   let password = form[form.id + ":password"].value;
   let passwordConfirm = form[form.id + ":passwordConfirm"].value;

   if(password === passwordConfirm)
      form.submit();
   else
      alert("Password and password confirm fields don't match");
}
