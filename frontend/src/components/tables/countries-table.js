import {Table} from "antd";
import {useEffect, useState} from "react";
import axios from "axios";
import {COUNTRIES_API} from "../../utils/api-constancts";
import {parseError} from "../../utils/parsers/xml/error-parser";
import {useSnackbar} from "notistack";
import {parseCountriesList} from "../../utils/parsers/xml/secret-parser";
import {Typography} from "antd";

const {Title} = Typography;

export function CountriesTable() {
    const {enqueueSnackbar, closeSnackbar} = useSnackbar();
    const [data, setData] = useState([]);

    useEffect(() => {
        getData();
    }, [])

    const getData = () => {
        axios
            .get(COUNTRIES_API)
            .then((response) => {
                const parsedResponse = parseCountriesList(response.data)
                setData(parsedResponse.items.map((element) => {
                    return {
                        ...element,
                        key: element.id
                    }
                }))
            })
            .catch((err) => {
                let error = parseError(err.response.data)
                enqueueSnackbar(error.message, {
                    autoHideDuration: 5000,
                    variant: "error"
                })
            });
    };


    return (
        <>
            <Title>Countries</Title>
            <div style={{maxWidth:300}}>


            <Table
                columns={[
                    {
                        title: 'Id',
                        dataIndex: 'id',
                        key: 'id',
                    },
                    {
                        title: 'Name',
                        dataIndex: 'name',
                        key: 'name',
                    },
                ]}
                rowKey="key"
                dataSource={data}
                bordered={true}
                pagination={false}
                scroll={{
                    y: 200,
                }}
            />
            </div>
        </>
    )
}