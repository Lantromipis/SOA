import {Layout, Menu} from "antd";
import {TableOutlined, LockOutlined, CalculatorOutlined} from "@ant-design/icons";
import {Link} from "react-router-dom";

const {Header} = Layout;

export default function CustomHeader({selectedMenuItem}) {
    const menuItems = [
        {
            icon: <TableOutlined />,
            label: (
                <Link to="/catalog">
                    Catalog
                </Link>
            ),
            key: 'catalog',
        },
        {
            icon: <CalculatorOutlined />,
            label: (
                <Link to="/agency">
                    Agency
                </Link>
            ),
            key: 'agency',
        },
        {
            icon: <LockOutlined />,
            label: (
                <Link to="/secret">
                    Secret
                </Link>
            ),
            key: 'secret',
        },
    ];

    return(
        <>
            <Header>
                <Menu
                    theme="dark"
                    mode="horizontal"
                    items={menuItems}
                    selectedKeys={[selectedMenuItem]}
                />
            </Header>
        </>
    )
}