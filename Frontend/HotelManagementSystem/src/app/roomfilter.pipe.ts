import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'roomfilter'
})
export class RoomfilterPipe implements PipeTransform {

  transform(items: any[], searchText: string): any[] {
    console.log(items)
    if (!items) {
      return [];
    }
    if (!searchText) {
      return items;
    }
    searchText = searchText.trim().toLowerCase();
    return items.filter(it => {
      return it.roomType.toLowerCase().includes(searchText);
    });
  }
}


