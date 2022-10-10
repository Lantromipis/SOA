import {useEffect, useState} from "react";
import {Button, Space, Table} from "antd";
import {parseFlatsPageResponseFromXml} from "../../utils/parsers/xml/flat-parser";
import axios from "axios";
import {useSnackbar} from "notistack";
import {parseError} from "../../utils/parsers/xml/error-parser";
import {
    filtersMapToLHSBrackets,
    filtersToLHSBrackets,
    parseSorterToQueryParam
} from "../../utils/parsers/table/sort-and-filter-parser";
import {getColumnSearchProps} from "./column-search";
import {FLATS_API} from "../../utils/api-constancts"
import {ReloadOutlined} from "@ant-design/icons";

export default function FlatsTable({pageSize}) {
    const {enqueueSnackbar, closeSnackbar} = useSnackbar();

    const [sortQueryParams, setSortQueryParams] = useState([]);
    const [filterModel, setFilterModel] = useState(new Map());
    const [currentPage, setCurrentPage] = useState(1);
    const [data, setData] = useState([]);
    const [totalCount, setTotalCount] = useState(0);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        getData(1, pageSize);
    }, [])

    const getData = (page, sort, filter) => {
        const queryParams = new URLSearchParams();

        queryParams.append("page", page);
        queryParams.append("pageSize", pageSize);
        if (sort?.length) {
            sort.forEach((element) => {
                queryParams.append("sort", element)
            })
        }
        if (filter) {
            filter.forEach((element) => {
                    queryParams.append("filter", element)
                }
            )
        }

        setLoading(true)

        axios
            .get(FLATS_API, {params: queryParams})
            .then((response) => {
                const parsedResponse = parseFlatsPageResponseFromXml(response.data)
                setData(parsedResponse.items.map((element) => {
                    return {
                        ...element,
                        key: element.id
                    }
                }))
                setTotalCount(parsedResponse.totalCount)
                setLoading(false)
            })
            .catch((err) => {
                let error = parseError(err.response.data)
                enqueueSnackbar(error.message, {
                    autoHideDuration: 5000,
                    variant: "error"
                })
            });
    };

    const handleTableChange = (pagination, filters, sorter) => {
        let sortQueryParams = [];
        let filterParams = [];

        const sortersArray = Array.from(sorter)

        if (sorter.hasOwnProperty("column")) {
            sortQueryParams[0] = parseSorterToQueryParam(sorter)
        } else if (sortersArray.length > 0) {
            sortQueryParams = sortersArray.map((element) => {
                return parseSorterToQueryParam(element)
            })
        }

        if (filters) {
            filterParams = filtersToLHSBrackets(filters)
        }

        let newFilterModel = new Map()

        Object.keys(filters).forEach((key) => {
                if (key && filters[key]) {
                    newFilterModel.set(key, [String([filters[key][0]]), filters[key][1]])
                }
            }
        )

        setCurrentPage(pagination.current)
        setSortQueryParams(sortQueryParams)
        setFilterModel(newFilterModel)

        getData(pagination.current, sortQueryParams, filterParams);
    };

    const handleFilterChange = (dataIndex, filterType, filterValue) => {
        if (filterType && filterValue) {
            const filtrationChanged =
                filterModel?.get(dataIndex)
                && (
                    filterModel?.get(dataIndex)[0] !== filterType
                    || filterModel?.get(dataIndex)[1] !== filterValue
                )

            if (filtrationChanged) {
                filterModel.set(dataIndex, [filterType, filterValue])
                getData(currentPage, sortQueryParams, filtersMapToLHSBrackets(filterModel))
                setFilterModel(filterModel)
            }
        }
    }

    const handleRefresh = () => {
        getData(currentPage, sortQueryParams, filtersMapToLHSBrackets(filterModel))
    }


    return (
        <>
            <Table
                columns={[
                    {
                        title: 'Id',
                        dataIndex: 'id',
                        key: 'id',
                        sorter: {multiple: 3},
                        sortDirections: ['ascend', 'descend'],
                        ...getColumnSearchProps('id', handleFilterChange)
                    },
                    {
                        title: 'Number of rooms',
                        dataIndex: 'numberOfRooms',
                        key: 'numberOfRooms',
                        sorter: {multiple: 3},
                        sortDirections: ['ascend', 'descend'],
                        ...getColumnSearchProps('numberOfRooms', handleFilterChange)
                    },
                    {
                        title: 'Area',
                        dataIndex: 'area',
                        key: 'area',
                        sorter: {multiple: 3},
                        sortDirections: ['ascend', 'descend'],
                        ...getColumnSearchProps('area', handleFilterChange)
                    },
                    {
                        title: 'Price',
                        dataIndex: 'price',
                        key: 'price',
                        sorter: {multiple: 3},
                        sortDirections: ['ascend', 'descend'],
                        ...getColumnSearchProps('price', handleFilterChange)
                    },
                    {
                        title: 'New',
                        dataIndex: 'new',
                        key: 'new',
                        sorter: {multiple: 3},
                        sortDirections: ['ascend', 'descend'],
                        ...getColumnSearchProps('new', handleFilterChange)
                    },
                    {
                        title: 'Transport',
                        dataIndex: 'transport',
                        key: 'transport',
                        sorter: {multiple: 3},
                        sortDirections: ['ascend', 'descend'],
                        ...getColumnSearchProps('transport', handleFilterChange)
                    },
                    {
                        title: 'Name',
                        dataIndex: 'name',
                        key: 'name',
                        sorter: {multiple: 4},
                        sortDirections: ['ascend', 'descend'],
                        ...getColumnSearchProps('name', handleFilterChange)
                    },
                    {
                        title: 'Creation date',
                        dataIndex: 'creationDate',
                        key: 'creationDate',
                        sorter: {multiple: 3},
                        sortDirections: ['ascend', 'descend'],
                        ...getColumnSearchProps('creationDate', handleFilterChange)
                    },
                    {
                        title: 'Height',
                        dataIndex: 'height',
                        key: 'height',
                        sorter: {multiple: 3},
                        sortDirections: ['ascend', 'descend'],
                        ...getColumnSearchProps('height', handleFilterChange)
                    },
                    {
                        title: 'Coordinates',
                        children: [
                            {
                                title: 'X',
                                dataIndex: ['coordinates', 'x'],
                                key: 'coordinates.x',
                                sorter: {multiple: 3},
                                sortDirections: ['ascend', 'descend'],
                                ...getColumnSearchProps('coordinates.x', handleFilterChange)
                            },
                            {
                                title: 'Y',
                                dataIndex: ['coordinates', 'y'],
                                key: 'coordinates.y',
                                sorter: {multiple: 3},
                                sortDirections: ['ascend', 'descend'],
                                ...getColumnSearchProps('coordinates.y', handleFilterChange)
                            },
                        ]
                    },
                    {
                        title: 'House',
                        children: [
                            {
                                title: 'Name',
                                dataIndex: ['house', 'name'],
                                key: 'house.name',
                                sorter: {multiple: 3},
                                sortDirections: ['ascend', 'descend'],
                                ...getColumnSearchProps('house.name', handleFilterChange)
                            },
                            {
                                title: 'Number of floors',
                                dataIndex: ['house', 'numberOfFloors'],
                                key: 'house.numberOfFloors',
                                sorter: {multiple: 3},
                                sortDirections: ['ascend', 'descend'],
                                ...getColumnSearchProps('house.numberOfFloors', handleFilterChange)
                            },
                            {
                                title: 'Number of lifts',
                                dataIndex: ['house', 'numberOfLifts'],
                                key: 'house.numberOfLifts',
                                sorter: {multiple: 3},
                                sortDirections: ['ascend', 'descend'],
                                ...getColumnSearchProps('house.numberOfLifts', handleFilterChange)
                            },
                            {
                                title: 'Year',
                                dataIndex: ['house', 'year'],
                                key: 'house.year',
                                sorter: {multiple: 3},
                                sortDirections: ['ascend', 'descend'],
                                ...getColumnSearchProps('house.year', handleFilterChange)
                            },
                        ]
                    }

                ]}
                rowKey="key"
                dataSource={data}
                onChange={handleTableChange}
                loading={loading}
                bordered={true}
                pagination={{
                    total: totalCount,
                    pageSize: pageSize,
                    showTotal: (total, range) => `${range[0]}-${range[1]} of ${total} items`
                }}
            />
            <Space style={{
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
            }}>
                <Button icon={<ReloadOutlined/>} onClick={handleRefresh} style={{}}>
                    Refresh
                </Button>
            </Space>
        </>
    );
}