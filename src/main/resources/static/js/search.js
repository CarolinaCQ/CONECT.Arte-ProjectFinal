const search = document.querySelectorAll('.search')[0];
const list = document.querySelectorAll('.list')[0];


const getGroups = async () => {
    let response = await fetch("http://localhost:8080/get-all");
    let data = await response.json();
    return data;
}

function setList(groups) {
    clearList();
    for (const group of groups) {
        const item = document.createElement('li');
        item.classList.add('list-item');
        const link = document.createElement('a');
        link.classList.add('item-link');
        link.setAttribute("href", `http://localhost:8080/groups/profile/${group.id}`);
        const text = document.createTextNode(group.name);
        link.appendChild(text);
        item.appendChild(link);
        list.appendChild(item);
    }
    if (groups.length === 0) {
        setNoResults();
    }
}

function clearList() {
    while (list.firstChild) {
        list.removeChild(list.firstChild);
    }
}

function setNoResults() {
    const item = document.createElement('li');
    item.classList.add('list-item');
    const text = document.createTextNode('No se encuentra');
    item.appendChild(text);
    list.appendChild(item);
}

function getRelevancy(value, searchTerm) {
    if (value === searchTerm) {
        return 2;
    } else if (value.startsWith(searchTerm)) {
        return 1;
    } else if (value.includes(searchTerm)) {
        return 0;
    } else {
        return -1;
    }
}

search.addEventListener('input', (event) => {
    const value = event.target.value;

    setTimeout(async () => {
        if (value && value.trim().length > 0) {

            const groups = await getGroups();

            setList(groups.filter(group => {
                return group.name.includes(value);
            }).sort((A, B) => {
                return getRelevancy(B.name, value) - getRelevancy(A.name, value);
            }));
        } else {
            clearList();
        }
    }, 2000);

})