const signUpButton = document.getElementById('signUpButton');
var container = document.getElementById('container');
const createAccount = document.getElementById('createAccountButton');

createAccount.addEventListener('click',addToContainer);
signUpButton.addEventListener('click',addToContainer);


function addToContainer(){
    container.classList.add('signUpForm-active')
}
