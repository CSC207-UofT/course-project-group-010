Refactoring Evidence
REFACTORED
- previously had CommentsGetter and UserGetter, but implemented a strategy pattern in CheckoutCommand. These both now implement PageGetter, and their methods were refactored and their names were changed.