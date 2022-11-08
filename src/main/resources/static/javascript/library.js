const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

const addBookForm = document.getElementById("add-book")
const toReadList = document.getElementById("to-read-container")
const readList = document.getElementById("read-container")

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
    let obtainedSelection = document.querySelector('input[name="obtained"]:checked').value;
    let isChecked = false;
        if (document.getElementById("read").checked) {
            isChecked = true;
        }
    console.log(isChecked);
    let bookObject = {
        title: titleInput.value,
        author: authorInput.value,
        genre: genreSelection.value,
        obtained: obtainedSelection,
        read: isChecked
    }
    await addBook(bookObject);
    
    titleInput.value = ''
    authorInput = ''
    genreSelection = ''
    obtainedSelection = ''
    readSelection = Boolean
}

async function addBook(obj) {
    const response = await fetch(`${baseURL}/user/` + userId, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status === 200) {
        return getBooks(userId);
    }
}

async function getBooks(userId) {
    await fetch(`${baseURL}/user/` + userId, {
        method: "GET",
        headers: headers
    })
    .then(res => res.json())
    .then(data => displayBooks(data))
    .catch(err => console.error(err))
}

async function handleMoveBook(bookId) {
    let isRead = document.querySelector(`#id-${bookId}`);
    let obj = {
        id: bookId,
        read: isRead.checked
    }
    await fetch(baseURL, {
        method: "PUT",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err))

    return getBooks(userId);
}

async function handleDelete(bookId) {
    await fetch(`${baseURL}/` + bookId, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))

    return getBooks(userId);
}

function createBookCard(obj) {
    console.log(obj);
    const bookCard = document.createElement('div');
    bookCard.classList.add('book-card')
    let checked = ''

    if (obj.read === true) {
        checked = "checked";
    } else {
        checked = "unchecked"
    }

    bookCard.innerHTML = `
    <div class="book-details"><p class="book">${obj.title}</p>
    <p class="author">By: ${obj.author}</p>
    <p class="genre">${obj.genre}</p>
    <p class="obtained">${obj.obtained}</p>
    </div>
    <div class="read-status">
    <input type="checkbox" ${checked} onclick='handleMoveBook(${obj.id})' id='id-${obj.id}'>Read?</input>
    </div>
    <button class="btn btn-info" onclick="handleDelete(${obj.id})">Delete</button>
    `

    return bookCard;
}

const displayBooks = (array) => {
    toReadList.innerHTML = ``;
    readList.innerHTML = ``;

    for (let i = 0; i < array.length; i++) {
        let bookCard = createBookCard(array[i]);
        console.log(bookCard);
        if(array[i].read === false) {
            toReadList.appendChild(bookCard)
        } else {
            readList.appendChild(bookCard)
        }
    }
}

addBookForm.addEventListener("submit", handleAddBook);




getBooks(userId);