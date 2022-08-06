import { Component }         from '@angular/core';
import {
  BreakpointObserver,
  Breakpoints
}                            from '@angular/cdk/layout';
import { Observable }        from 'rxjs';
import {
  map,
  shareReplay
}                            from 'rxjs/operators';
import {NavigationComponent} from '@pages/layout/navigation.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  constructor(private breakpointObserver: BreakpointObserver) {
  }

  title = 'ui-new111';

}
