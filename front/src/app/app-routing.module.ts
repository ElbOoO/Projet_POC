import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormComponent } from './form/form.component';
import { PoggerComponent } from './pogger/pogger.component';

const routes: Routes = [
  {path:"gregLeSang", component:FormComponent},
  {path:"totoleNoob", component:PoggerComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
