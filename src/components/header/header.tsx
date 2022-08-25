import React, { PureComponent, useEffect } from "react";
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { StyleSheet, Text, View } from "react-native";
import { HeaderDto } from "~/share/Header.state";
import { TopHeader } from "./topHeader";
import DropDownItem from "../ui-components/dropdowns.item";
import './header.scss'
export default class Header extends React.PureComponent<{}, HeaderDto> {
    constructor(props: any) {
        super(props);
        this.state = {
            linkColor: "text-dark",
            NavLinkClassName: ['text-dark'],
            dropDowns: [
                {
                    title: 'Fiction',
                    items: [
                        'genres', 'xyz', 'abc', 'abgd'
                    ]
                },
                {
                    title: 'NoFiction',
                    items: [
                        'genres', 'xyz', 'abc', 'abgd'
                    ].reverse()
                }
            ]
        }
       

    }

    componentDidMount(){
        let customItem = document.getElementsByClassName('item-custom');
        console.log(customItem);
        
        // let resl = customItem[0].getElementsByClassName('dropdown-menu')[0];
        
    }

    getNavLinkClassName = () => {
        return this.state.NavLinkClassName.reduce((acc, prev) => {
            return acc += ' ' + prev;
        }, '')
    }

    getNavLinksArray = (): Array<string> => {
        return ['Home', 'Fiction', 'NoFiction'];
    }
    render(): React.ReactNode {
        return (
            <>
                <TopHeader />
                <Navbar bg="white" variant="dark">
                    <Container style={styles.subTitle}>
                        <Nav style={styles.subTitle} as="ul">
                            <Nav.Link className={this.getNavLinkClassName()} as="li">
                                <a href="#">Home</a>
                            </Nav.Link>
                        </Nav>
                        {
                            this.state.dropDowns?.map(dropdown =>
                                <DropDownItem title={dropdown.title}
                                    items={dropdown.items.reverse()} />)
                        }
                    </Container>
                </Navbar>
            </>
        )
    }
}

const styles = StyleSheet.create({
    subTitle: {
        display: "flex",
        flexDirection: "row",
        justifyContent: "center",
        // position:'relative'
    },
});