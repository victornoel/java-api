/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.zold.api;

import java.util.Iterator;

/**
 * Network implementation.
 *
 * @since 0.1
 * @todo #5:30min We must figure out how to 'load' some network. Loading the
 *  network will be loading a local JSON file that contains data on all
 *  remote nodes that we know about; we must have a pre configured set of
 *  remote nodes built in too. See whitepaper for details.
 */
public final class RtNetwork implements Network {

    /**
     * {@link Remote} nodes.
     */
    private final Iterable<Remote> nodes;

    /**
     * Constructor.
     * @param remotes Remotes of the network
     */
    RtNetwork(final Iterable<Remote> remotes) {
        this.nodes =  remotes;
    }

    // @todo #5:30min Implement scoring algorithm when paying taxes. Scoring
    //  algorithm must select the node with the highest score and with score
    //  >= 16. There are some tests for the scoring algorithm in NetworkTest:
    //  remove ignore tag from them after algorithm implementation.
    @Override
    public void push(final Wallet wallet) {
        this.nodes.forEach(
            remote -> remote.push(wallet)
        );
    }

    // @todo #5:30min Implement pull method. Pulling a wallet from the
    //  network should return all the wallets with that id in the
    //  network merged together. After the implementation
    //  NetworkTest.pullIsNotYetImplemented() have to be uncommented and
    //  test if pull method is behaving correctly.
    @Override
    public Wallet pull(final Long id) {
        throw new UnsupportedOperationException("pull(id) not supported");
    }

    @Override
    public Iterator<Remote> iterator() {
        return this.nodes.iterator();
    }
}
