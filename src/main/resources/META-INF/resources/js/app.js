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
                .get(window.location.origin + '/' + this.id)
                .then(response => (
                    this.inputNameModel = response.data.name,
                    this.inputFoodModel = response.data.food,
                    this.checkVeganModel = response.data.vegan))
                .catch(error => { console.log(error) })
        },
        readAllEntries: function () {
            axios
                .get(window.location.origin + '/all')
                .then(response => (this.results = response.data))
                .catch(error => { console.log(error) })
        },
        createEntry: function () {
            if (this.inputNameModel == '' || this.inputFoodModel == '') {
                this.inputNameModel = 'INVALID'
                this.inputFoodModel = 'INVALID'
            }
            axios
                .post(window.location.origin + '/create',
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
                .put(window.location.origin + '/update',
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
                .delete(window.location.origin + '/delete',
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
