new Vue({
    el: '#app',
    data() {
        return {
            results: [],
            id: 0,
            inputNameModel: '',
            inputFoodModel: '',
            checkVeganModel: false
        }
    },
    mounted() {
        this.readAllEntries()
    },
    methods: {
        readEntry: function () {
            axios
                .get('http://localhost:8080/' + this.id)
                .then(response => (
                    this.inputNameModel = response.data.name,
                    this.inputFoodModel = response.data.food,
                    this.checkVeganModel = response.data.vegan))
                .catch(error => { console.log(error) })
        },
        readAllEntries: function () {
            axios
                .get('http://localhost:8080/all')
                .then(response => (this.results = response.data))
                .catch(error => { console.log(error) })
        },
        createEntry: function () {
            if (this.inputNameModel == '' || this.inputFoodModel == '') {
                this.inputNameModel = 'INVALID'
                this.inputFoodModel = 'INVALID'
            }
            axios
                .post('http://localhost:8080/create',
                    {
                        name: this.inputNameModel,
                        food: this.inputFoodModel,
                        vegan: this.checkVeganModel
                    })
                //.then(response => (this.results = response.data))
                .catch(error => { console.log(error) })

            this.waitBeforeReadAll(180)
        },
        updateEntry: function () {
            axios
                .put('http://localhost:8080/update',
                    {
                        id: this.id,
                        name: this.inputNameModel,
                        food: this.inputFoodModel,
                        vegan: this.checkVeganModel
                    })
                //.then(response => (this.results = response.data))
                .catch(error => { console.log(error) })

            this.waitBeforeReadAll(180)
        },
        deleteEntry: function () {
            axios
                .delete('http://localhost:8080/delete',
                    {
                        data: {
                            id: this.id,
                            name: this.inputNameModel,
                            food: this.inputFoodModel,
                            vegan: this.checkVeganModel
                        }
                    })
                //.then(response => (this.results = response.data))
                .catch(error => { console.log(error) })

            this.waitBeforeReadAll(180)
        },
        waitBeforeReadAll: async function (time) {
            let promise = new Promise((res, rej) => {
                setTimeout(() => res(this.readAllEntries()), time)
            });

            let result = await promise;

            console.log(result)
        }
    }
})

/*
async function waitAsync() {
    let promise = new Promise((res, rej) => {
        setTimeout(() => res("Now it's done!"), 1000)
    });

    // wait until the promise returns us a value
    let result = await promise;

    // "Now it's done!"
    // alert(result);
    console.log(result)
}
*/
