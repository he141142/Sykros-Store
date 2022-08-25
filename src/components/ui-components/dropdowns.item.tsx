import NavDropdown from 'react-bootstrap/NavDropdown';
import React, { PureComponent } from "react";
import { dropDownDto } from './index.dto';
import { StyleSheet, Text, View } from "react-native";
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

export default class DropDownItem extends PureComponent<dropDownDto, any> {

    constructor(props: dropDownDto) {
        super(props);
        this.state = {
            name: 'Link',
            count: 1,
            active: false,
        }
    }

    constructGridLayout = (amountOneCol: number): any[][] => {
        let subArr: Array<any> = []

        let resp: Array<Array<any>> = this.props.items.reduce<Array<Array<string>>>((acc, item, index, arr) => {
            subArr.push(item);

            if ((index + 1) % amountOneCol === 0 || index === arr.length - 1) {
                acc.push(subArr);
                subArr = [];
            }

            return acc;
        }, []);
        return resp;
    }


    
    componentDidMount = ()=>{
        let customItem = document.getElementsByClassName('item-custom');
        console.log(customItem);
        customItem[0].addEventListener("click",()=>{
            this.setState((prev: any)=> ({
                active: !prev.active
            }));
        })
    
    }


    componentDidUpdate(){
        console.log(this.state);
        console.log('updated');
    }


    render(): React.ReactNode {

        return (
            <>
                <NavDropdown className='item-custom' style={styles.UlDropDown} title={this.props.title} id="navbarScrollingDropdown" as="ul">
                    {

                        this.constructGridLayout(2).map((elm: Array<any>) =>
                            <Row >
                                <Col className= 'sub-it' style={styles.widthItem}><a href="">{elm[0]}</a> </Col>
                                <Col className= 'sub-it' style={styles.widthItem}><a href="">{elm[1]}</a></Col>
                            </Row>
                        )

                    }
                </NavDropdown>
            </>
        )
    }

}

const styles = StyleSheet.create({
    UlDropDown: {
        margin: 0,
        //   position: 'absolute'
    },
    widthItem: {
        width: '20em'
    }
});