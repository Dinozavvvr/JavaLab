function validateItem(className, id, errorClassName) {
    let errItem = document.getElementsByClassName(className)[0];
    if (errItem !== undefined) {
        let item;
        item = document.getElementById(id)
        item.classList.add(errorClassName)
    }
}

function validation() {
    validateItem("email_error", "email", "input_error")
    validateItem("login_error", "login", "input_error")
    validateItem("password_error", "password", "input_error")
}

function reset(id, className) {
    console.log(id + " " +  className)
    document.getElementById(id).classList.remove(className)
}