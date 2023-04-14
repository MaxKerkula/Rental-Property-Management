<template>
<div class="payment-container">
  <div class="payment" v-bind:class="{ overdue: payment.overdue }">
    <h2 class="payment-title">{{ payment.title }}</h2>
    <p class="payment-amount">{{ payment.amount | currency }}</p>
    <p class="payment-due-date">Due: {{ payment.dueDate | date }}</p>
    <div class="button-container">
      <button class="make-payment" v-on:click.prevent="makePayment">Make Payment</button>
    </div>
  </div>
</div>
</template>

<script>
export default {
    name: 'payments',
    props: {
    payment: {
      type: Object,
      required: true
    }
  },
//   methods: {
//     makePayment() {
//       // make payment logic
//     }
//   },
  filters: {
    currency(value) {
      return `$${value.toFixed(2)}`;
    },
    date(value) {
      return new Date(value).toLocaleDateString();
    }
  }
}
</script>

<style>
.payment-container {
    display:flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
}
.payment {
  border: 1px solid #ccc;
  padding: 10px;
  margin-bottom: 10px;
}
.overdue {
  background-color: #ffcdd2;
}
.payment-title {
  font-size: 1.2em;
  margin: 0 0 5px 0;
}
.payment-amount {
  font-weight: bold;
  margin: 0 0 5px 0;
}
.payment-due-date {
  margin: 0;
}
.button-container {
  display: flex;
  justify-content: flex-end;
}
.make-payment {
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 3px;
  padding: 5px 10px;
  cursor: pointer;
}
</style>
