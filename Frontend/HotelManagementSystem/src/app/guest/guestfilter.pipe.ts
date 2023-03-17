import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'guestfilter'
})
export class GuestfilterPipe implements PipeTransform {

  transform(items: any[], searchText: string): any[] {
    if (!items) {
      return [];
    }
    if (!searchText) {
      return items;
    }
    searchText = searchText.trim().toLowerCase();
    return items.filter(it => {
      return it.name.toLowerCase().includes(searchText);
    });
  }

}
