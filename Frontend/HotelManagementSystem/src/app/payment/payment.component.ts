import { Component } from '@angular/core';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent {

  paymentHandler: any = null;
  constructor() {}
  ngOnInit() {
    this.invokeStripe();
  }
  makePayment() {
    const paymentHandler = (<any>window).StripeCheckout.configure({
      key: 'pk_test_51MbpSRSHOrw7vTJnKnc9UlWNGM5q4tg9nIqAEsq6ikpAKb9a2fWp6513TWLgxFegEUksHbVWIbPonTXbLHwpRNmY00pPoc7z3o',
      locale: 'auto',
      token: function (stripeToken: any) {
       
        console.log(stripeToken);
        alert('Payment is Successful!');
       
      },
    });
    paymentHandler.open({
      name: 'Positronx',
      description: '3 widgets',
     
    });
  }
  invokeStripe() {
    if (!window.document.getElementById('stripe-script')) {
      const script = window.document.createElement('script');
      script.id = 'stripe-script';
      script.type = 'text/javascript';
      script.src = 'https://checkout.stripe.com/checkout.js';
      script.onload = () => {
        this.paymentHandler = (<any>window).StripeCheckout.configure({
          key: 'pk_test_51MbpSRSHOrw7vTJnKnc9UlWNGM5q4tg9nIqAEsq6ikpAKb9a2fWp6513TWLgxFegEUksHbVWIbPonTXbLHwpRNmY00pPoc7z3o',
          locale: 'auto',
          token: function (stripeToken: any) {
            console.log(stripeToken);
            alert('Payment has been successfull!');
          },
        });
      };
      window.document.body.appendChild(script);
    }
  }
}


