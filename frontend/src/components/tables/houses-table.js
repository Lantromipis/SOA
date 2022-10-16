import {Button, Form, InputNumber, Table} from "antd";
import {useEffect, useState} from "react";
import axios from "axios";
import {COUNTRIES_API, HOUSES_API} from "../../utils/api-constancts";
import {parseError} from "../../utils/parsers/xml/error-parser";
import {useSnackbar} from "notistack";
import {parseCountriesList, parseHousesList} from "../../utils/parsers/xml/secret-parser";
import {Typography} from "antd";
import {validateIntegerGreaterThanZero} from "../forms/validators";
import {useForm} from "antd/es/form/Form";

const {Title} = Typography;

export function HousesTable() {
    const {enqueueSnackbar, closeSnackbar} = useSnackbar();
    const [data, setData] = useState([]);

    const [sponsorForm] = useForm();

    const getData = (e) => {
        axios
            .get(`${HOUSES_API}/countries/${e['id']}/houses`)
            .then((response) => {
                const parsedResponse = parseHousesList(response.data)
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
            <Title>Houses</Title>
            <Form
                form={sponsorForm}
                onFinish={getData}
                layout="inline"
                labelCol={{span: 8}}
                wrapperCol={{span: 16}}
            >
                <Form.Item
                    style={{width: 200}}
                    label="Country id"
                    name="id"
                    rules={[
                        {required: true, message: 'Please input ID!'},
                        {validator: validateIntegerGreaterThanZero}
                    ]}
                >
                    <InputNumber/>
                </Form.Item>
                <Form.Item>
                    <Button type="primary" onClick={sponsorForm.submit} style={{width: 150}}>
                        Get houses
                    </Button>
                </Form.Item>
            </Form>
            <div style={{maxWidth: 800}}>
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
                        {
                            title: 'Number of floors',
                            dataIndex: 'numberOfFloors',
                            key: 'numberOfFloors',
                        },
                        {
                            title: 'Number of lifts',
                            dataIndex: 'numberOfLifts',
                            key: 'numberOfLifts',
                        },
                        {
                            title: 'Year',
                            dataIndex: 'year',
                            key: 'year',
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