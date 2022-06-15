let form1 = document.querySelectorAll('.form1')[0];
        let form2 = document.querySelectorAll('.form2')[0];
        let form3 = document.querySelectorAll('.form3')[0];

        let next1 = document.querySelectorAll('.next1')[0];
        let next2 = document.querySelectorAll('.next2')[0];

        let back1 = document.querySelectorAll('.back1')[0];
        let back2 = document.querySelectorAll('.back2')[0];

        let step1 = document.querySelectorAll('.step1')[0];
        let step2 = document.querySelectorAll('.step2')[0];
        let step3 = document.querySelectorAll('.step3')[0];

        next1.addEventListener('click', () => {
            form1.style.display = "none";
            form2.style.display = "flex";
            form3.style.display = "none";
            step2.style.background = "#44093A";
            step2.style.color = "white";
        })

        next2.addEventListener('click', () => {
            form1.style.display = "none";
            form2.style.display = "none";
            form3.style.display = "flex";
            step3.style.background = "#44093A";
            step3.style.color = "white";
        })

        back1.addEventListener('click', () => {
            form1.style.display = "flex";
            form2.style.display = "none";
            form3.style.display = "none";
            step2.style.background = "white";
            step2.style.color = "black";
            step1.style.background = "#44093A";
            step1.style.color = "white";
        })

        back2.addEventListener('click', () => {
            form1.style.display = "none";
            form2.style.display = "flex";
            form3.style.display = "none";
            step3.style.background = "white";
            step3.style.color = "black";
        })