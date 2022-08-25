import { dropDownDto } from './../components/ui-components/index.dto';
import { type } from "os";

type HeaderDto = {
    linkColor: string,
    NavLinkClassName: Array<string>,
    dropDowns?:dropDownDto[]
}

export type { HeaderDto }