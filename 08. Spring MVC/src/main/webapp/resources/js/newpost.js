var loadFile = function(event) {

    var label = document.getElementById('file-label');
    label.textContent = 'add other photo';
    label.classList.add('active');

    var output = document.getElementById('preview');
    output.src = URL.createObjectURL(event.target.files[0]);
    output.onload = function() {
        URL.revokeObjectURL(output.src);
    }

};


function submitForm() {
    let form = document.getElementById('form');
    console.log(document.getElementById('image').value);
    form.submit();
}