const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

const submitForm = document.getElementById("add-book")
const toReadContainer = document.getElementById("to-read-container")
const readContainer = document.getElementById("read-container")

const headers = {
    'Content-Type':'application/json'
}

const baseURL = "http://localhost:8080/api/v1/books"

function handleLogout() {
    let c = document.cookie.split(";");
    for (let i in c) {
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

const handleAddBook = async (e) => {
    e.preventDefault();

    let titleInput = document.querySelector('input[name="title"]');
    let authorInput = document.querySelector('input[name="author"]');
    let genreSelection = document.querySelector('option[value]');
    let obtainSelection = document.querySelector.querySelector('input[name="obtain"]:checked');
    let isChecked = false;
        if (document.getElementById("read").checked) {
            isChecked = true;
        }
    console.log(isChecked);
    let bookObject = {
        title: titleInput.value,
        author: authorInput.value,
        genre: genreSelection.value,
        obtain: obtainSelection.value,
        read: isChecked
    }

    handleAddBook

}

async function addBook(obj) {
    const response = await fetch(`${baseURL}user/${userId}`, {
        method: "POST",
        b
    })
}