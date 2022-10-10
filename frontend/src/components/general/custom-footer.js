import {Layout} from "antd";

const {Footer} = Layout;

export default function CustomFooter() {
    return(
        <>
            <Footer
                style={{
                    textAlign: 'center',
                    width: '100%',
                }}
            >
                SOA lab created by Artem Bakin from group P34101 ©2022
            </Footer>
        </>
    )
}